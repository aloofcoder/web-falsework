package github.aloofcoder.generate.util;

import github.aloofcoder.generate.pojo.entity.ColumnEntity;
import github.aloofcoder.generate.pojo.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author hanle
 * @date 2020-08-10
 */
public class CodeGenerateUtils {

    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generate.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
            throw new AppException("获取配置文件失败，", e);
        }
    }


    public static void buildCode(Map<String, String> table,
                                 List<Map<String, String>> columns, ZipOutputStream zip) {
        Configuration config = getConfig();
        boolean hasBigDecimal = false;
        boolean hasList = false;
        boolean hasDate = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), columnToJava(columnEntity.getDataType()));
            columnEntity.setAttrType(attrType);


            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasList && "array".equals(columnEntity.getExtra())) {
                hasList = true;
            }
            if (!hasDate && attrType.equals("Date")) {
                hasDate = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        String mainPath = config.getString("mainPath");
        mainPath = StringUtils.isBlank(mainPath) ? "github.aloofcoder" : mainPath;
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pkName", tableEntity.getPk().getColumnName());
        map.put("pk", tableEntity.getPk());
        map.put("PKName", WordUtils.capitalize(tableEntity.getPk().getAttrName()));
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasList", hasList);
        map.put("hasDate", hasDate);
        map.put("mainPath", mainPath);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        VelocityContext context = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), config.getString("moduleName"))));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new AppException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
//
//
        templates.add("templates/vm/Dao.java.vm");
        templates.add("templates/vm/Mapper.xml.vm");
        templates.add("templates/vm/Service.java.vm");
        templates.add("templates/vm/ServiceImpl.java.vm");
        templates.add("templates/vm/Entity.java.vm");
        templates.add("templates/vm/Controller.java.vm");
        templates.add("templates/vm/PageDTO.java.vm");
        templates.add("templates/vm/DTO.java.vm");
        templates.add("templates/vm/DetailVO.java.vm");
//        templates.add("template/Entity.java.vm");
//        templates.add("template/menu.sql.vm");
//        templates.add("template/Controller.java.vm");
//
//        templates.add("template/index.vue.vm");
//        templates.add("template/add-or-update.vue.vm");
//        templates.add("templates/Service.java.vm");
//        templates.add("templates/serviceImpl.jav.vma");
//        templates.add("templates/Mapper.xml.vm");
        return templates;
    }


    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }
        if (template.contains("Entity.java.vm") || template.contains("MongoEntity.java.vm")) {
            return packagePath + "pojo" + File.separator + "entity" + File.separator + className + "Entity.java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + "I" + className + "Service.java";
        }

        if (template.contains("PageDTO.java.vm")) {
            return packagePath + "pojo" + File.separator + "dto" + File.separator + className + "PageDTO.java";
        }

        if (template.contains("DTO.java.vm")) {
            return packagePath + "pojo" + File.separator + "dto" + File.separator + className + "DTO.java";
        }

        if (template.contains("DetailVO.java.vm")) {
            return packagePath + "pojo" + File.separator + "vo" + File.separator + className + "DetailVO.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator + moduleName + File.separator + className + "Mapper.xml";
        }

        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }

        if (template.contains("index.vue.vm")) {
            return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
                    File.separator + moduleName + File.separator + className.toLowerCase() + ".vue";
        }

        if (template.contains("add-or-update.vue.vm")) {
            return "main" + File.separator + "resources" + File.separator + "src" + File.separator + "views" + File.separator + "modules" +
                    File.separator + moduleName + File.separator + className.toLowerCase() + "-add-or-update.vue";
        }

        return null;
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String[] tablePrefixArray) {
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        return columnToJava(tableName);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }


}
