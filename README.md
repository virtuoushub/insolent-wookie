# Problem Statement
We have to develop an application that synchronizes data in a RDMS database from an external source via web services. The REST web service will return company data, products data and company to product relationship data in a xml format

## Requirements
* The application will synchronize the database everyday once at 12:01am.
* The app should be able to process inserts updates and deletes in order. The data returned from the web service will be treated as Master
* Duplicates will be identified based on primary column.
* Only deltas should be processed based upon what changed since the last time the database was synced up. The updates to the db should be committed only if the entire set is processed successfully.
* A report of the sync status such as to how many company insert/update and deletes were processed should be mailed out to a configurable address
* The xml schema is well defined (attached is the sample).

## Solution Requirements
* The solution should include a high level class and sequence diagram explaining the flow
* Database Data Model to store the data [include primary key and foreign keys]
* List Software Frameworks such as Spring, Hibernate etc that will be used to implement each layer
* Pseudo code for each component/layer detailed enough to explain the processing logic.
* Running application with compiled code that can demoed during the interview with H2 Database or HSQLDB [Optional]
* Test strategy for testing each component/layer
* Clearly state assumptions made for requirements that ambiguous, missing or incomplete

## See Also
* http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSenderImpl.html
* http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronTrigger.html
* http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/oxm/jaxb/Jaxb2Marshaller.html
