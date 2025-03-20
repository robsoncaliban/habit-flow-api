FROM maven:3.9.9-amazoncorretto-17

EXPOSE 8080

RUN mkdir -p /home/habit-flow

COPY . /home/habit-flow

WORKDIR /home/habit-flow
