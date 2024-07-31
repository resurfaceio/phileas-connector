# phileas-connector
Phileas functions for Trino

This [Trino](https://trino.io) connector uses [Phileas](https://github.com/philterd/phileas) to detect and redact PII tokens.
Simple scalar functions are provided to redact `varchar` data provided by any other Trino data source.

[![CodeFactor](https://www.codefactor.io/repository/github/resurfaceio/phileas-connector/badge)](https://www.codefactor.io/repository/github/resurfaceio/phileas-connector)

## Dependencies

* Java 22
* Trino SPI
* [philterd/phileas](https://github.com/philterd/phileas) 

## Configuring Trino

```
1. Install Trino
download and expand tarball to local directory
create etc directory as described here: https://trino.io/docs/current/installation/deployment.html
export TRINO_HOME=$HOME/...

2. Create $TRINO_HOME/etc/catalog/phileas.properties:
connector.name=phileas
connector.policy.file=/Users/me/policy.txt

3. Build the connector and redeploy
mvn clean package && rm -rf $TRINO_HOME/plugin/phileas && cp -r ./target/phileas-connector-0.0.1 $TRINO_HOME/plugin/phileas

4. Start Trino
cd $TRINO_HOME
bash bin/launcher run
```

## Redacting Strings

Use DBeaver or your favorite SQL editor to try the `phileas_redact` function.

```
select phileas_redact('my email is rik@resurfacd.io')
--> my email is ****************
```

---
Copyright 2024 Philterd, LLC @ https://www.philterd.ai