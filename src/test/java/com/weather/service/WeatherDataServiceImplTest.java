package com.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.domain.CurrentWeatherDataVO;
import com.weather.openweathermap.client.OpenWeatherMap;
import com.weather.openweathermap.domain.OpenWeatherMapResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class WeatherDataServiceImplTest {


    @Mock
    private OpenWeatherMap mockOpenWeatherMap;
    @InjectMocks
    private WeatherDataService weatherDataService = new WeatherDataServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnOKStatusCodeWithQuoteInformation() throws Exception {

        File file = new File("src/test/resources/sample-weather-map-response.json");
        ObjectMapper objectMapper = new ObjectMapper();
        OpenWeatherMapResponse weatherMapResponse = objectMapper.readValue(file, OpenWeatherMapResponse.class);

        when(mockOpenWeatherMap.weatherInfoFor("123456")).thenReturn(weatherMapResponse);

        CurrentWeatherDataVO currentWeatherDataVO = weatherDataService.currentWeatherInfoFor("123456");

        assertNotNull(currentWeatherDataVO);
        assertEquals("London", currentWeatherDataVO.getCityName());
        assertEquals("02-May-2017", currentWeatherDataVO.getCurrentDate());
        assertEquals("overcast clouds", currentWeatherDataVO.getWeatherDescription());
        assertEquals("6.33C", currentWeatherDataVO.getTemperatureInCelsius());
        assertEquals("43.39F", currentWeatherDataVO.getTemperatureInFahrenheit());
        assertEquals("05:30 AM", currentWeatherDataVO.getSunriseTime());
        assertEquals("08:25 PM", currentWeatherDataVO.getSunsetTime());
    }
    
    
    public class LocalDynamodb {
    private DynamoDBProxyServer server;
    private AmazonDynamoDB amazonDynamoDB;
    private final String port =  getAvailablePort();

    private WickesLogger logger = WickesLoggerFactory.getLogger(this.getClass());

    public LocalDynamodb() throws Exception {
        System.setProperty("sqlite4java.library.path", "native-libs");

        amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("user", "pwd")))
                .withEndpointConfiguration(new EndpointConfiguration("http://localhost:" + port, "local"))
                .build();
    }

    @PostConstruct
    public void before() throws Exception {
            this.server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", port});
            server.start();
            logger.info("in memory dynamodb started");
    }

    @PreDestroy
    protected void after() throws Exception {
        if (server != null) {
            server.stop();
        }

        logger.info("in memory dynamodb stopped");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public void createDynamoDBTable(Class... clazzes) {
        Arrays.stream(clazzes)
                .forEach(clazz -> createDynamoDBTable(clazz, empty()));
    }

    public void createDynamoDBTableWithProjectAll(Class... clazzes) {
        Arrays.stream(clazzes)
                .forEach(clazz -> createDynamoDBTable(clazz,
                        Optional.of(new Projection().withProjectionType(ProjectionType.ALL))));
    }

    public void createDynamoDBTable(Class clazz, Optional<Projection> projection) {
        DynamoDBMapper dynamoDBMapper =  new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(clazz);

        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput().withReadCapacityUnits(1L)
                .withWriteCapacityUnits(1L);
        createTableRequest.withProvisionedThroughput(provisionedThroughput);

        if (createTableRequest.getGlobalSecondaryIndexes() != null) {
            for (GlobalSecondaryIndex index : createTableRequest.getGlobalSecondaryIndexes()) {
                index.setProvisionedThroughput(provisionedThroughput);
                projection.ifPresent(index::setProjection);
            }
        }

        amazonDynamoDB.createTable(createTableRequest);
    }

    public AmazonDynamoDB getAmazonDynamoDB() {
        return this.amazonDynamoDB;
    }

    private String getAvailablePort() {
        try (final ServerSocket serverSocket = new ServerSocket(0)) {
            return String.valueOf(serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException("Available port was not found", e);
        }
    }
}

    
}
