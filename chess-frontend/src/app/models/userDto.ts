export interface UserDto extends UserRegisterDto {
  roles: string[];
  elo: number;
}

export interface UserRegisterDto extends UserLoginDto {
  name: string;
  surname: string;
  email: string;
}

export interface UserLoginDto {
  username: string;
  password: string;
}
