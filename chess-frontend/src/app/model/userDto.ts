export interface UserDto {
  name: string;
  surname: string;
  username: string;
  email: string;
  password: string;
}

export interface UserLoginDto {
  username: string;
  password: string;
}
