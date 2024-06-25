export interface User{
    firstname : String,
    lastname : String,
    usernmae : String,
    email : String,
    password : String,
    role? : Role,
    birthDate? : Date
}

export enum Role{
    USER,
    ADMIN
}