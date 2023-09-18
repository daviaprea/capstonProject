import { Genres } from "./genres"

export interface Movie {
  readonly id:number,
  title:string,
  //cover:number[]
  cover:Blob,
  trailerLink:string,
  releaseDate:Date,
  duration:number,
  director:string,
  actors:string,
  description:string,
  genres:Genres[],
  isTridimensional:boolean
}
