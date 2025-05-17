###

Note: Without @Path on the class, Quarkus will not register it as a REST resource,
and your endpoints won't be available.

```
Issue	                                        Fix
----------------------------------------------------------------------------------------------------
No class-level @Path	                        Add @Path("/api") (or whatever base route you want)
Missing HTTP method on invalidate endpoint	    Add @GET to /invalidate/cache method
Not working	                                    Because Quarkus couldn't register this as 
                                                a REST controller without these annotations
```