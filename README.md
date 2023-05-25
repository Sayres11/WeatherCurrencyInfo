## The provided code consists of multiple Java classes that are part of a weather and currency information retrieval application. Here's a summary of what's going on in the code:
![image](https://github.com/Sayres11/WeatherCurrencyInfo/assets/44787029/1baa8061-c39c-4470-80df-cdc62ba86e30)

### Currency class:
Represents a currency and provides methods to retrieve exchange rates and NBP (National Bank of Poland) currency information.
Uses the getContentFromUrl method to fetch data from specific URLs.
Implements the getExchangeRate method to retrieve the exchange rate for a given currency code and weather object.
Implements the NBP method to fetch NBP currency information based on the provided weather object.
Provides the getFrom method to retrieve the currency code.

### GUI class:
Extends the JFrame class and represents the graphical user interface of the application.
Initializes and displays a JFrame window with labels for weather, currency, and NBP information.
Uses a JFXPanel to embed a WebView component for displaying Wikipedia information related to the weather city.
Implements the run method as part of the Runnable interface.
Provides the wiki method to load the Wikipedia page related to the weather city.

### Service class:
Represents a service that combines weather and currency information.
Initializes a weather object and currency object based on the provided country.
Implements methods to retrieve weather information, exchange rates, and NBP rates.
Provides getter methods for accessing weather, currency, and current currency code.

### Weather class:
Represents weather information for a specific location.
Constructs a URL for retrieving weather data from the OpenWeatherMap API.
Implements methods to retrieve JSON weather data, extract temperature, set the currency code, and retrieve relevant data such as city, country, and currency code.

### Main class:
Contains the main method used for testing and running the application.
Creates a service object, retrieves weather JSON, exchange rates, NBP rates, and initializes the GUI.
