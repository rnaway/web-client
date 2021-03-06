package ru.siblion.nesterov.client.servlets;

import ru.siblion.nesterov.client.utils.ClientLogger;
import ru.siblion.nesterov.client.type.Action;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by alexander on 23.01.2017.
 */
public class DownloadFileServlet extends HttpServlet {
    @EJB
    private ClientLogger clientLogger;

    private static final String DOWNLOAD_PATH = "http://localhost:7001/logreader-1.0.1/resources/restWebService/";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String fileName = URLEncoder.encode(request.getParameter("fileName"), "UTF-8"); // сохраняет знак плюс в utl "+"

        URL url = new URL(DOWNLOAD_PATH + fileName);
        clientLogger.log(request.getRemoteUser(), Action.DOWNLOAD, "filename: " + fileName);
        BufferedInputStream inStream = new BufferedInputStream(url.openStream());

        OutputStream outStream = response.getOutputStream();
        response.setContentType("application/force-download"); // делает так, чтобы файл скачивался, а не открывался в браузере
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inStream.close();
        outStream.flush();
        outStream.close();
    }
}
