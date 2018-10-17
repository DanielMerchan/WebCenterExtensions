# WebCenterExtensions
Extensions (Task Flows and Portlets) which brings new integrations that can be used within Oracle WebCenter Portal.

# Releases
- **12.2.1.2-0**: Base Release including WCP-BAM integration. It loads the BAM Dashboards by using ProxyPage BAM 
- **12.2.1.3-0 (work in progress)**: Added LoginInternalAPI and LoginJavaAPI Task Flows that can be modified, embedded and changed to embed a Login Form within a WCP Portal Page or Page Template. *Note: BAM Integration is not working in this release, working on changing the dependency with the BAM Connection as it is deprecated*

# Extensions / Components

## BAM Dashboard

![alt text](https://raw.githubusercontent.com/DanielMerchan/WebCenterExtensions/master/BAM-WCP.png)

This project includes the following artifacts:

- **BAM Dashboard Task Flow**: Embeds a BAM Dashboard Task Flow by using the **BAM Composer Proxy Page**

### Desirable
Single Sign-On between Oracle WebCenter Portal and Oracle BAM is required to avoid login multiple times.

### Installation
- Configure a BAM Connection using Enterprise Manager:
   - Select WebCenter Portal -> Server -> WebCenter Portal Application
   - In the Application Menu -> ADF -> ADF Connections
   - Setup a new BAM Connection to your BAM Server.
- Deploy the ADF JAR Library as a shared-library using the Deployer Project and reference it from your Portal Extension shared library (extend.spaces.webapp.war)

### Usage:
- Add BAM Dashboard Task Flow into WCP Resource Catalog
- Add BAM Dashboard Task Flow into a WCP Portal Page.
- Configure BAM Dashboard Task Flow Input Parameters. (A BAM Dashboard Proxy Page URL looks like http://wcp12c:7004/bam/composer/faces/proxypage?DashboardParameters=%28ProcessSelection%3D%5BALL%5D%3B%29&dashboard=ProcessHealth&project=ProcessAnalytics_v2)
The importan part is *DashboardParameters=%28ProcessSelection%3D%5BALL%5D%3B%29&dashboard=ProcessHealth&project=ProcessAnalytics_v2* The rest is built by using the BAM Connection name configured.

   - **BAM Connection Name**: Provide the ADF Connection Name created for BAM. (E.g. BAMConnection) E.g.: BAMConnection

   - **Dashboard**: Name of the BAM Dashboard to embed. It corresponds to the URL parameter *dashboard* E.g.: ProcessHealth

   - **Dashboard Parameters** (Optional): URL Parameters of the Dashboard. It corresponds to the URL parameter *DashboardParameters* E.g.: ProcessSelection%3D[ALL]%3B

   - **Project**: BAM Project the dashboard belongs. It matches to the URL parameter *project*. E.g.: ProcessAnalytics_v2

- Configure Display Options:
   - Strecht Content: True
- Configure Content Style:
   - Give a minimum height. E.g.: 800px
   
## Login
This project contains the following artifacts:
- **LoginInternalAPI Task Flow**: This Task Flow uses Internal WebCenter Portal 12c API (EL Expressions) to perform Login. It is compatible with OAM / WCP Login. *Note: Using Internal API is riskly as it can be modified in future releases / patches"
- **LoginJavaAPI Task Flow**: This Task Flow uses Jakarta EE (formerly called Java EE) HTTPServletRequest API for doing authentication. *Note: The WebLogic Servlets Authenticators has been deprecated in 12c"

### Installation
- Deploy the ADF JAR Library as a shared-library using the Deployer Project and reference it from your Portal Extension shared library (extend.spaces.webapp.war)

### Usage
- Add it to a Resource Catalog and add the Task Flow as part of your WCP Portal Pages.
- Add it to a Resource Catalog and add the Task Flow as part of your WCP Portal Page Templates.
- Add it manually to a Page Template in design time by usign ``<af:region>`` and registering it manually in the corresponding pageDefinition.
