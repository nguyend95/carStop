package eu.epptec.carStop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

@RestController
@RequestMapping("/test")
public class testController {
    private final RequestMappingHandlerMapping handlerMapping;
    protected DataSource dataSource;

    @Autowired
    public testController(RequestMappingHandlerMapping handlerMapping,
                          DataSource dataSource) {
        this.handlerMapping = handlerMapping;
        this.dataSource = dataSource;
    }

    @GetMapping("/schema")
    public String showTables() throws Exception {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
        ResultSet schemas = metaData.getSchemas();
        StringBuilder stringBuilder = new StringBuilder();

        while (schemas.next()){
            stringBuilder.append("Schema>").append(schemas.getString("SCHEMA_NAME")).append("|>");
            while (tables.next()) {
                String tableName=tables.getString("TABLE_NAME");
                stringBuilder.append(tableName).append("\n");
                ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");
                while (columns.next()) {
                    String columnName=columns.getString("COLUMN_NAME");
                    stringBuilder.append("\t").append(columnName);
                }
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    @GetMapping
    public String test(){
        return "Hello";
    }

    @GetMapping("/endpointdoc")
    public void show(Model model) {
        model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());
    }
}
