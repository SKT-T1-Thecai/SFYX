FROM openjdk:12
WORKDIR /app/
COPY ./* ./
RUN javac OGanalyse.java