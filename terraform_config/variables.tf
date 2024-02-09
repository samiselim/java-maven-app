
variable "ssh_ip" {
    default = "0.0.0.0/0"
}
variable "image_name" {
    default = "amzn2-ami-kernel-5.10-hvm-2.0.20240131.0-x86_64-gp2"
}
variable "region" {
    default = "eu-west-3"
}
variable "zone" {
    default = "eu-west-3a"
}
variable "vpc_cidr_block" {
    default = "10.0.0.0/16"
}
variable "subnet_cidr_block" {
    default = "10.0.0.0/24"
}
variable "instance_type" {
    default = "t2.micro"
}
variable "prefix" {
    default = "dev"
}