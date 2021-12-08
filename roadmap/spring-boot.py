from urllib.request import urlretrieve

from diagrams import Cluster, Diagram
from diagrams.aws.compute import EC2
from diagrams.aws.database import RDS
from diagrams.aws.network import ELB
from diagrams.onprem.database import PostgreSQL, Mysql, Mariadb, Mongodb, Neo4J, Cassandra, Couchbase, Mssql
from diagrams.custom import Custom
from diagrams.onprem.vcs import Git
from diagrams.programming.language import Java
from diagrams.programming.framework import Spring

jhipster_url = "https://raw.githubusercontent.com/jhipster/jhipster-artwork/main/logos/JHipster%20bowtie.png"
jhipster_icon = "jhipster.png"
urlretrieve(jhipster_url, jhipster_icon)

maven_url = "https://raw.githubusercontent.com/jhipster/jhipster.github.io/main/images/logo/icons/maven.png"
maven_icon = "maven.png"
urlretrieve(maven_url, maven_icon)

gradle_url = "https://avatars.githubusercontent.com/u/124156"
gradle_icon = "gradle.png"
urlretrieve(gradle_url, gradle_icon)

spring_security_url = "https://pbs.twimg.com/profile_images/1235983944463585281/AWCKLiJh_400x400.png"
spring_security_icon = "spring_security.png"
urlretrieve(spring_security_url, spring_security_icon)

liquibase_url = "https://avatars.githubusercontent.com/u/438548"
liquibase_icon = "liquibase.png"
urlretrieve(liquibase_url, liquibase_icon)

with Diagram("Spring Boot Project", show=False, direction="TB"):

    # init = Custom("init", jhipster_icon)
    init = Git("init")

    with Cluster("Build Tool"):
        # gradle = Custom("Gradle", gradle_icon)
        maven = Custom("", maven_icon)

    with Cluster("Spring Boot"):
        java = Java("")
        spring_boot = Spring("Spring Boot")

    with Cluster("Server"):
        spring_boot_mvc = Spring("Spring MVC")
        spring_security_jwt = Custom("Security JWT", spring_security_icon)

    with Cluster("Database"):
        # neo4j = Neo4J("Neo4J")
        # couchbase = Couchbase("Couchbase")
        # cassandra = Cassandra("Cassandra")
        # mongodb = Mongodb("MongoDB")
        # mssql = Mssql("MSSQL")
        # mariadb = Mariadb("MariaDB")
        # mysql = Mysql("MySQL")
        postgresql = PostgreSQL("PostgreSQL")
        liquibase = Custom("Liquibase", liquibase_icon)

    init >> [ maven ] >> java
    java >> spring_boot >> spring_boot_mvc
    spring_boot_mvc >> spring_security_jwt
    spring_boot >> [ postgresql ]
    [ postgresql ] >> liquibase
    # spring_boot >> [ mysql, postgresql, mariadb, mssql, mongodb, neo4j, cassandra, couchbase ]
    # [ mysql, postgresql, mariadb, mssql ] >> liquibase
