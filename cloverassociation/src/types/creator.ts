import type {cProduct} from "@/types/product.ts";

export interface creator {
    id: number;
    name: string;
    description: string;
    avatar: string;
    birthDate: string;
    address: string;
    phone: string;
    email: string;
    experience: string;
    skill: string;
    createdAt: string;
    updatedAt: string;
}

export type creators = Array<creator>;

export interface cCreator {
    id: number;
    name: string;
    description: string;
    avatar: string;
    birthDate: string;
    address: string;
    phone: string;
    email: string;
    experience: string;
    skill: string;
    createdAt: string;
    updatedAt: string;
    products: cProduct[];
}

export type cCreators = Array<cCreator>;