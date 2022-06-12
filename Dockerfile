FROM lolgab/scala-native-docker as builder

COPY . .
RUN sbt frontend/fastLinkJS
RUN sbt backendNative/nativeLink

FROM nginx/unit:1.27.0-minimal

COPY /config.json /docker-entrypoint.d/
COPY /www /www

COPY --from=builder frontend/target/scala-2.13/frontend-fastopt/ /www/
RUN chmod -R 777 /www
COPY --from=builder backend/native/target/scala-2.13/example-out /app/todo

EXPOSE 8081
