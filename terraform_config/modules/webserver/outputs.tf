output "instance_object" {
    value = aws_instance.instance1
}
output "image_name" {
    value = data.aws_ami.aws_image_latest.name
}