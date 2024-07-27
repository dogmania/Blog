plugins {
    id("java")
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // 스프링 데이터 JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // 인메모리 데이터베이스
    runtimeOnly("com.h2database:h2")

    // 롬복
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // 타임리프
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // 스프링 시큐리티
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.test {
    useJUnitPlatform()
}