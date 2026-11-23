# SmartGreen - Smart Greenhouse Management System

Java 17 + JavaFX 17 + Maven demonstration application.

## Run
1. Open the project folder in IntelliJ IDEA or VS Code.
2. Ensure JDK 17 or newer is configured.
3. Allow Maven to download dependencies.
4. Run:

   ```bash
   mvn clean javafx:run
   ```

Main class: `com.smartplant.Main`

## Project structure

```text
src/main/java/com/smartplant/
├── Main.java
├── controllers/       # JavaFX page controllers and navigation
├── models/            # Plant, User, Alert and Sensor domain classes
└── services/          # Demo data and sensor/plant/user/alert logic

src/main/resources/
├── fxml/              # Main shell and individual page views
└── css/               # Shared application stylesheet
```

## Navigation design
- `Main.java` loads `main-layout.fxml` once.
- `main-layout.fxml` contains the shared sidebar and the central `contentArea`.
- `NavigationController` receives a navigation callback from `MainController`.
- `MainController` loads only the selected page into `contentArea`.
- Individual page FXML files do not include another sidebar.
- Double-click a plant in Plants to open its Plant Details page.

## Demo scope / current limitations
- Sensor values, plants, users and alerts are sample in-memory data.
- Environmental readings are simulated; no physical sensors are connected.
- No database, authentication, email delivery, or persistent settings are configured.
- User and plant creation/edit/delete workflows are not implemented in this demo.
- The Settings page validates threshold inputs but does not persist them.

These limitations are stated intentionally so the demonstration UI does not imply that hardware or database integration already exists.
