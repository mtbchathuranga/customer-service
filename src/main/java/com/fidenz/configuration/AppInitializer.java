package com.fidenz.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fidenz.model.dto.Customer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@PropertySource(value = {"classpath:application.properties"})
@Configuration
public class AppInitializer implements WebApplicationInitializer {

    @Autowired
    private Environment environment;

    public static final Logger LOGGER = Logger.getLogger(AppInitializer.class.getName());
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container
                .addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        File file = new File("D:\\Dev\\dilan\\src\\main\\resources\\csv");
        if (!file.isDirectory()) {
            new File(environment.getRequiredProperty("D:\\Dev\\dilan\\src\\main\\resources\\csv")).mkdir();
            file.mkdir();

        } else {
            File csvFile = new File("D:\\Dev\\dilan\\src\\main\\resources\\csv\\customers.csv");
            if (csvFile.exists()) {
                csvFile.delete();
            }
            File fileName = new File("D:\\Dev\\dilan\\src\\main\\resources\\UserData.json");
            File jsonFile = new File(fileName.getAbsolutePath());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, Customer.class);
                List<Customer> customers = objectMapper.readValue(jsonFile, collectionType);

                FileWriter writer = new FileWriter("D:\\Dev\\dilan\\src\\main\\resources\\csv\\customers.csv");

                String commaDelimeter = ",";
                String newLineSeperator = "\n";
                String fileHeader = "_id,index,age,eyeColor,name,gender,company,email," +
                        "phone,number,street,city,state,zipcode,about,registered,latitude," +
                        "longitude,tags";
                writer.append(fileHeader);
                writer.append(newLineSeperator);
                customers.forEach(customer -> {
                    try {
                        writer.append(customer.get_id()).append(commaDelimeter);
                        writer.append(customer.getIndex()).append(commaDelimeter);
                        writer.append(customer.getAge()).append(commaDelimeter);
                        writer.append(customer.getEyeColor()).append(commaDelimeter);
                        writer.append(customer.getName()).append(commaDelimeter);
                        writer.append(customer.getGender()).append(commaDelimeter);
                        writer.append(customer.getCompany()).append(commaDelimeter);
                        writer.append(customer.getEmail()).append(commaDelimeter);
                        writer.append(customer.getPhone()).append(commaDelimeter);
                        writer.append(customer.getAddress().getNumber()).append(commaDelimeter);
                        writer.append(customer.getAddress().getStreet()).append(commaDelimeter);
                        writer.append(customer.getAddress().getCity()).append(commaDelimeter);
                        writer.append(customer.getAddress().getState()).append(commaDelimeter);
                        writer.append(customer.getAddress().getZipcode()).append(commaDelimeter);
                        writer.append(customer.getAbout().replaceAll("[\n\r]","")).append(commaDelimeter);
                        writer.append(customer.getRegistered()).append(commaDelimeter);
                        writer.append(customer.getLatitude()).append(commaDelimeter);
                        writer.append(customer.getLongitude()).append(commaDelimeter);
                        writer.append("\"" + String.join(",",customer.getTags()) + "\"");
                        writer.append(newLineSeperator);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                });
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
