# Running the project example
---------------------------------------------------------------------

## Running the deliveries service with Maven/Jetty.

    cd rs-deliveries/rs-deliveries-service
    mvn jetty:run


## Running the deliveries client application

- Configure `rs-deliveries/rs-deliveries-client/src/main/resources/ConfigurationParameters.properties`
  for specifying the client project service implementation (XML or JSON) and the port number 
  of the web server in the endpoint address (7070 for Jetty)
  
- Change to `rs-deliveries-client` folder

    cd rs-deliveries/rs-deliveries-client


- AddCustomer

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-addCustomer 'Universidade da Coruña' 12345678J 'Elviña s/n'"

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-addCustomer 'Ayuntamiento de A Coruña' 11111111J 'María Pita s/n'"

- ChangeState

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-changeState 1 REJECTED"
	
	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-changeState 1 SENT"

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-changeState 1 DELIVERED"

- FindByCustomer

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-findByCustomer 1 0 2"

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-findByCustomer 1 0 10"

- DeleteCustomer

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-deleteCustomer 123"

	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-deleteCustomer 1"
	
	mvn exec:java -Dexec.mainClass="es.udc.rs.deliveries.client.ui.DeliveryServiceClient" -Dexec.args="-deleteCustomer 2"
