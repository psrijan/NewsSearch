package com.orgware.project.newssearch.newsservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orgware.project.newssearch.model.Message;
import com.orgware.project.newssearch.model.News;
import com.orgware.project.newssearch.newssaveservice.NewsSaveService;
import com.orgware.project.newssearch.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class NewsService implements INewsService {

    private final String NEWS_API_URL= "https://newsapi.org/v2/everything?q=bitcoin&apiKey=dad48ba99c6547e795d918dac24b1fc5&pageSize=100&page=";
    private final int HTTP_RESPONSE_SUCCESS = 200;
    private final int TOO_MANY_REQUEST = 429;

    private final String STOP_SERVICE = "STOP";
    private final String START_SERVICE = "START";

    private int pageCount=0;
    private int totalRecords=0;

    private final String ARTICLE_NODE_NAME = "articles";

    private Timer timer;

    @Autowired
    private NewsSaveService newsSaveService;


    @PostConstruct
    private void init() {
        startNewsFetcherService(this);
    }


    @Override
    public void startNewsFetcherService(final NewsService newsService){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                newsService.fetchNewsAPI();
            }
        };
        timer.schedule(timerTask , 1000 , 10000);

    }

    /**
     * This function fetches JSON from an external news API and
     * passes it to the Repository to save to Elastisearch
     *
     */
    @Override
    public void fetchNewsAPI() {

        try {
            URL url = new URL(NEWS_API_URL+ (++pageCount));
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept" , "application/json");

            if(conn.getResponseCode()== TOO_MANY_REQUEST) {
                System.out.println("Too Many Requests to API Server | Stopping Fetch Service");
                timer.cancel();
            } else if(conn.getResponseCode()!=HTTP_RESPONSE_SUCCESS) {
                throw new RuntimeException("Http response error: Response" + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String jsonString="";
            String output;
            while((output = br.readLine())!= null){
                jsonString+=output;
            }
            System.out.println("Full Json String ");
            System.out.println(jsonString);

            List<News> newsList = convertStringToJson(jsonString);

            for(News n : newsList ) {
                newsSaveService.saveNews(n);
            }

            conn.disconnect();

        } catch (MalformedURLException ex ) {
            ex.printStackTrace();
        } catch (IOException ex ) {
            ex.printStackTrace();
        } catch (RuntimeException ex ){
            ex.printStackTrace();
        }
    }


    /**
     * The received data from api is initially in String Format
     * Here I will convert the String to a JsonObject
     */
    @Override
    public List<News> convertStringToJson(String jsonString) {
        //Make into a json tree
        //take out specific node information
        //make java class

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            if (jsonNode == null) {
                throw new NullPointerException("No data received from the api");
            }
            JsonNode articleNode = null;
            if (jsonNode.get("status").toString().contains("ok")) {
                articleNode = jsonNode.get(ARTICLE_NODE_NAME);
            }

            System.out.println("Printing Only Articles ");
            System.out.println(articleNode.toString());
            int i=0;
            JsonNode indivArticle;
            List<News> newsList = new ArrayList<>();

            return newsList;

        } catch (NullPointerException | IOException ex  ) {
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * This method enables or disables the service depending on the
     * option passed from the rest end point.
     * @param option
     * @return
     */
    @Override
    public Message toggleService(String option) {
        if(option.equalsIgnoreCase(STOP_SERVICE)) {
            timer.cancel();
        } else if(option.equalsIgnoreCase(START_SERVICE)) {
            startNewsFetcherService(this);
        }

        return provideMessageStatus(option);
    }


    /**
     * Helper function to provide Status and
     * description depending on the condition.
     */
    private Message provideMessageStatus(String option) {
        Message message;
        if(option.equalsIgnoreCase(STOP_SERVICE)) {
            message =new Message();
            message.setStatus("Stopped");
            message.setMessage("Request Processed | Server Stopped");
        } else if (option.equalsIgnoreCase(START_SERVICE)) {
            message =new Message();
            message.setStatus("Started");
            message.setMessage("Request Processed | Server Started");
        } else {
            message =new Message();
            message.setStatus("Invalid");
            message.setMessage("Not a valid Request");
        }
        return message;
    }
}
