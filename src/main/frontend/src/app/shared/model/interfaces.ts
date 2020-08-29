export interface Response {
  code: number;
  status: string;
  message: string;
  httpStatus: number;
}

export interface BaseResponse {
  response: Response
}

export interface Planet {
  planetId: string;
  planetName: string;
}

export interface PlanetListResponse extends BaseResponse {
  planets: Planet[];
}

export interface ShortestPathRequest {
  source: string,
  destination: string;
}
