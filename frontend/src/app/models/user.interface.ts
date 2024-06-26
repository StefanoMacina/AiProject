export interface RegistrationRequest{
    firstname : String,
    lastname : String,
    username : String,
    email : String,
    password : String,
    role? : Role,
    birthDate? : Date,
    gender : Gender
}

export interface UserDto{
    firstname : String,
    lastname : String,
    username : String,
    Role : Role,
    email : String,
    gender : Gender
}

export enum Role{
    USER,
    ADMIN
}

export enum Gender{
    MALE,
    FEMALE
}

export interface LoginRequest{
    username : String,
    password : String
}