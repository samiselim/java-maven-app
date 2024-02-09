output "image" {
    value = module.web_server.image_name
}

output "public_ip" {
    value = module.web_server.instance_object.public_ip
}