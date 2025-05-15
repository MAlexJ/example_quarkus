### SCHEDULER REFERENCE GUIDE

link: https://quarkus.io/guides/scheduler-reference

Modern applications often need to run specific tasks periodically.
There are two scheduler extensions in Quarkus.
The quarkus-scheduler extension brings the API and a lightweight in-memory scheduler implementation.
The quarkus-quartz extension implements the API from the quarkus-scheduler
extension and contains a scheduler implementation based on the Quartz library.
You will only need quarkus-quartz for more advanced scheduling use cases,
such as persistent tasks and clustering.