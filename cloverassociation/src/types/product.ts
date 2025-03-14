export interface product {
    id: number,
    avatar:string,
    name:string,
    version:number,
    authorName:string,
    readme:string,
    description:string,
    updateContent:string,
    link:string,
    createdAt:string,
    updatedAt:string,
}

export interface cProduct {
    id: number,
    avatar:string,
    name:string,
    version:number,
    authorId:number,
    authorName:string,
    readme:string,
    description:string,
    updateContent:string,
    link:string,
    createdAt:string,
    updatedAt:string,
}

export type products = Array<product>