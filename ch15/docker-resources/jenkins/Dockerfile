FROM jenkins/jenkins:jdk21

# Switch to root user for installations
USER root

# Install Docker CLI
RUN apt-get update && \
    apt-get install -y docker.io && \
    apt-get clean

# Install kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" && \
    install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl && \
    rm kubectl

# Add Jenkins user to Docker group for permissions
RUN usermod -aG docker jenkins

# Switch back to Jenkins user
USER jenkins