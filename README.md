# WebCenterExtensions
Extensions (Task Flows and Portlets) which brings new integrations that can be used within Oracle WebCenter Portal.

**Compatibility**: Oracle ADF 12.2.1.2 / Oracle WebCenter Portal 12.2.1.2

**Java JDK**: 1.8+

# Releases
- **12.2.1.2-0 (Future)**: Base Release including WCP-BAM integration. It loads the BAM Dashboards by using ProxyPage BAM Composer.
- **12.2.1.2-1 (Future)**: Enhancements on BAM Integration (Lazy Loading, Error Handling, Logging and clean code).
- **12.2.1.3-0 (Future)**: Upgrade to 12.2.1.3 release.

#### Installation
TBA

#### Usage:
TBA

## Projects / Modules

### BAM (WCP-BAM Integration)
This project includes the following artifacts:

- **BAM Dashboard Task Flow**: Embeds a BAM Dashboard Task Flow by using the **BAM Composer Proxy Page**

#### Recommended
Single Sign-On between Oracle WebCenter Portal and Oracle BAM is required to avoid multiple login.

### CommonView
This project includes the following artifacts:
- **Task Flow Template**: Including Lazy Loading mechanism and common Input Parameters for WCP Custom Task Flows.
