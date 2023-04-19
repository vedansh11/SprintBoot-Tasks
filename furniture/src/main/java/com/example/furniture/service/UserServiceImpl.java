package com.example.furniture.service;
import com.example.furniture.model.UserModel;
import com.example.furniture.repository.UserRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.awt.image.BufferedImage;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    String path = "/home/vedansh/Desktop/Reports/";
    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<InputStreamResource> exportReport(String reportFormat) throws FileNotFoundException, JRException {

        List<UserModel> userModels = userRepository.findAll();

        //Load a file and compile it
        File file = ResourceUtils.getFile("classpath:report.jrxml");

        //Jasper report for compiling
        //Jasper complier manager takes jrxml as input and returns a Jasper report object
        //the object represent the complied report design and contain all the information requireed to fill the report
        //This object then is further used to fill the reports with data and export in Csv,pdf,html
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        //Getting our data source
        //The JRBeanCollectionDataSource class is a part of the JasperReports library and is used to wrap a collection of Java objects and provide them as a data source to a report.
        // In this case, the userModels collection is being wrapped by the JRBeanCollectionDataSource.
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(userModels);

        //Adding parameters for showing some info
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("CreatedBy", "Vedansh");

        //Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrBeanCollectionDataSource);


        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\report.pdf");

            File file1 = new File("/home/vedansh/Desktop/Reports/\\report.pdf");
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-disposition", "inline;filename=" + file1.getName());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file1));

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file1.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(resource);

        }
        return (ResponseEntity<InputStreamResource>) ResponseEntity.ok();
    }

    @Override
    public List<UserModel> getallusers() {
    return userRepository.findAll();
    }


    public void createBarCode128(String fileName) {

        try {
            Code128Bean bean = new Code128Bean();
            final int dpi = 160;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

            bean.doQuietZone(false);

            //Open output file
            File outputFile = new File(path + fileName + ".JPG");

            FileOutputStream out = new FileOutputStream(outputFile);

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Generate the barcode
            bean.generateBarcode(canvas, fileName);

            //Signal end of generation
            canvas.finish();


            System.out.println("Bar Code is generated successfully…");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}