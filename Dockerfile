FROM openjdk:11-jdk-slim AS build
WORKDIR /app
RUN apt-get update && apt-get install -y \
    curl \
    zip \
    unzip \
    bash
RUN curl -s "https://get.sdkman.io" | bash && \
    bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install gradle 8.8" && \
    echo 'source "$HOME/.sdkman/bin/sdkman-init.sh"' >> $HOME/.bashrc
ENV PATH="$HOME/.sdkman/candidates/gradle/current/bin:$PATH"
COPY . .
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && gradle build"

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8800
ENTRYPOINT ["java", "-cp", "/app/resources:/app/classes:/app/libs/*", "ru.bezdar.skip.app.Application"]
