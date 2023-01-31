# TestModuloTech

## Architecture
The package tree is presented as is:
- app: Contains the application class
- data : Contains the classes used for access to the datasource (json, database)
- di : Contains the class for dependency injection
- domain : Contains all the business code
- ui : Contains all the classes for the UI
- util : Contains common code (constants, enums)

In the data layer:
- We use a repository class to fetch data from the network or in local and populate the database
- There's two data source class : one for the network calls and one for storing data in local.

In the domain layer:
- There's all the use case classes which returns a state as a result.

In the ui layer:
- There's the viewmodel
- A mapper which converts the state returned by the use case class into an uiModel
- An uiModel which is a model more suitable for ui display (ex.: converting a timestamp in a formatted date string)
- The fragment itself


## Libraries used
- Retrofit for network calls
- Moshi for json serialization
- Koin for dependency injection
- Jetpack navigation for navigate between screens
- Room for database
