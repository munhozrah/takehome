## takehome
Takehome is an application that implements a single endpoint to gather information from MockAPI and add/update in MailChimp. It's built using
- Open JDK 17
- Spring Boot 2.7.4
- Spring MVC

### Documentation
More about this app [here](https://docs.google.com/document/d/1jaTWBRPbMNBoDPt1BohpsF4Vo1XRopXN8vklZKcam88/edit?usp=sharing)

## Installation

This applications expects the following variables as environments variables or passed in mvn command:

| Variable Name     | Variable Value                                                |
| ----------------- | ------------------------------------------------------------- |
| mockapiEndpoint   | Mockapi used as source of contacts                            |
| mailChimpListId   | MailChimp list id. Can be found using their API               |
| mailChimpUsername | Username for basic authentication. The default is "anystring" |
| mailChimpPassword | Password is the apikey generated in MailChimp dashboard.      |

Use maven to build

```bash
mvn clean install
```

The easiest way to run is using maven, considering the environement variables are set
```bash
mvn spring-boot:run
```

## Usage
Call endpoint and it will trigger the synchronization
```bash
GET /contacts/sync
```
The endpoint will return the synced contacts in the format:
```json
{
	"syncedContacts": 1,
  "contacts": [
		{
			"firstName": "Amelia",
      "lastName": "Earhart",
      "email": "amelia_earhart@gmail.com"
		}
	]
}
```

