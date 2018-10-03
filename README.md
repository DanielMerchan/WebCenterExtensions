# WebCenterExtensions
Extensions (Task Flows and Portlets) which brings new integrations that can be used within Oracle WebCenter Portal.

**Compatibility**: Oracle ADF 12.2.1.2 / Oracle WebCenter Portal 12.2.1.2

**Java JDK**: 1.8+

# Releases
- **12.2.1.2-0 (Current)**: Base Release including WCP-BAM integration. It loads the BAM Dashboards by using ProxyPage BAM 

# Extensions / Components

## BAM Dashboard
This project includes the following artifacts:

- **BAM Dashboard Task Flow**: Embeds a BAM Dashboard Task Flow by using the **BAM Composer Proxy Page**

### Desirable
Single Sign-On between Oracle WebCenter Portal and Oracle BAM is required to avoid login multiple times.

### Installation
- Configure a BAM Connection using Enterprise Manager:
   - Select WebCenter Portal -> Server -> WebCenter Portal Application
   - In the Application Menu -> ADF -> ADF Connections
   - Setup a new BAM Connection to your BAM Server.
- Deploy the ADF JAR Library as a shared-library and reference it from your Portal Extension shared library (extend.spaces.webapp.war)

### Usage:
- Add BAM Dashboard Task Flow into WCP Resource Catalog
- Add BAM Dashboard Task Flow into a WCP Portal Page.
- Configure BAM Dashboard Task Flow Input Parameters.
   - **BAM Connection Name**: Provide the ADF Connection Name created for BAM. (E.g. BAMConnection)
   - **Dashboard**: Name of the BAM Dashboard to embed (E.g: ProcessAnalyticsSpringboard)
   - **Dashboard Parameters** (Optional): URL Parameters of the Dashboard (E.g.: ?...)
   - **Project**: BAM Project the dashboard belongs to (E.g: ProcessAnalytics_v2)
