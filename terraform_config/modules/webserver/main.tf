resource "aws_security_group" "security_group1" {
    name = "${var.prefix}-sg"
    vpc_id = var.vpc_id
    ingress {
        # here ther is from and to because you can define a range of ports ﻻ
        from_port = 22
        to_port = 22
        protocol = "tcp"
        cidr_blocks = [var.ssh_ip]
    }
    ingress  {
        # here ther is from and to because you can define a range of ports ﻻ
        from_port = 8080
        to_port = 8080
        protocol = "tcp"
        cidr_blocks = [var.ssh_ip]
        
    }
     ingress  {
        # here ther is from and to because you can define a range of ports ﻻ
        from_port = 5432
        to_port = 5432
        protocol = "tcp"
        cidr_blocks = [var.ssh_ip]
        
    }
    egress {
        from_port = 0
        to_port = 0 
        protocol = "-1"  #any protocol
        cidr_blocks = ["0.0.0.0/0"]
        prefix_list_ids = []
    }
    tags = {
      Name: "${var.prefix}-sg"
    }
}
data "aws_ami" "aws_image_latest" {
    most_recent = true
    owners = ["amazon"]
    filter {
      name = "name"
      values = [var.image_name]
    }

}

resource "aws_instance" "instance1" {
    # ami = "ami-0bf160d8f27d39442"
    ami = data.aws_ami.aws_image_latest.id
    instance_type = var.instance_type

    subnet_id = var.subnet_id # reference the module called subnet1_module 
    vpc_security_group_ids = [aws_security_group.security_group1.id]
    availability_zone = var.zone

    associate_public_ip_address = true 
    key_name = "tf_key"


    user_data = file("entry_script.sh")

    tags = {
      Name: "${var.prefix}-server"
    }

}