# phileas-connector
Phileas functions for Trino

This [Trino](https://trino.io) connector uses [Phileas](https://github.com/philterd/phileas) to detect and redact PII tokens.
Simple scalar functions are provided to redact `char` and `varchar` data provided by any other Trino data source.
