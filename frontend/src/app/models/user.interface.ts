export interface User{
    firstname : String,
    lastname : String,
    username : String,
    email : String,
    password : String,
    role? : Role,
    birthDate? : Date,
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