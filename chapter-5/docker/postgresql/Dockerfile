# Use the official PostgreSQL image as the base
FROM postgres:latest

# Set environment variables
ENV POSTGRES_DB auction
ENV POSTGRES_USER admin
ENV POSTGRES_PASSWORD auction123

# Optional: Add custom initialization scripts
# COPY ./init.sql /docker-entrypoint-initdb.d/

# Expose the default PostgreSQL port
EXPOSE 5432

# The entrypoint and command are inherited from the base image,
# so no need to re-specify unless you need custom behavior.
