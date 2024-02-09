resource "aws_vpc" "vpc1" {
    cidr_block = var.vpc_cidr_block
    tags = {
        Name: "${var.prefix}-vpc"
    }
}
module "subnet1_module" {
   source = "./modules/subnet"
   vpc_id = aws_vpc.vpc1.id
   subnet_cidr_block = var.subnet_cidr_block 
   zone = var.zone
   sub_prefix = var.prefix
}
module "web_server" {
    source = "./modules/webserver"
    vpc_id = aws_vpc.vpc1.id
    subnet_id = module.subnet1_module.subnet_object.id
    subnet_cidr_block = var.subnet_cidr_block
    zone = var.zone
    prefix = var.prefix
    instance_type = var.instance_type
    image_name = var.image_name
    ssh_ip = var.ssh_ip
}