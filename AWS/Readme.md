# Ec2 Setup  and connect with Putty

## Step 1: Launch an EC2 Instance
1. Log in to AWS Management Console.
2. Go to EC2 → Instances → Launch instances.
3. Fill in the required details:
4. Name: e.g., MyEC2

5. AMI (Amazon Machine Image): Choose Amazon Linux 2 or Ubuntu (common choices).
6. Instance type: Start with t2.micro (Free Tier eligible).
 Key pair (login):
7.  Create a new key pair (type = RSA, format = .pem).
8.  Download and save it safely (you’ll need it to connect).
Network settings:
9. Allow SSH (port 22).   Also allow HTTP/HTTPS if you plan to run a web server.
10. Storage: Default is fine for testing (8 GB).
11. Click Launch Instance.

##  Step 2: Convert .pem key to .ppk (for PuTTY)
1. PuTTY doesn’t support .pem keys directly, so you must convert it.
2. Open PuTTYgen (comes with PuTTY installation).
3. Click Load → select your downloaded .pem file.
Change file type to All Files to see it.
4. Click Save private key → save as .ppk.

## Step 3: Get Your EC2 Public IP
1. Go to EC2 → Instances.
2. Select your instance.
3. Copy the Public IPv4 address.

##  Step 4: Connect Using PuTTY
1. Open PuTTY.
In Host Name (or IP address): enter ec2-user@<Public_IP>
2. For Amazon Linux: username = ec2-user.
For Ubuntu: username = ubuntu.

In Category → Connection → SSH → Auth → Credentials, browse and select your .ppk key file.

Click Open.

First time, accept the security warning.

You should now be logged in to your EC2 instance.

Example login command inside PuTTY (Amazon Linux):

ec2-user@3.88.xx.xx
