resource "aws_subnet" "subnet1" {
  vpc_id = var.vpc_id
  cidr_block = var.subnet_cidr_block
  availability_zone = var.zone
  tags = {
    Name: "${var.sub_prefix}-subnet"
  }
}

resource "aws_internet_gateway" "gate_way1" {
    vpc_id = var.vpc_id
    tags = {
      Name: "${var.sub_prefix}-igw"
    }
}
resource "aws_route_table_association" "rtb_association1" {
    subnet_id = aws_subnet.subnet1.id
    route_table_id = aws_route_table.route_table1.id
}
resource "aws_route_table" "route_table1" {
    vpc_id = var.vpc_id

    route {
        cidr_block ="0.0.0.0/0"
        gateway_id = aws_internet_gateway.gate_way1.id
    }
    tags = {
      Name: "${var.sub_prefix}-rtb"
    }
}