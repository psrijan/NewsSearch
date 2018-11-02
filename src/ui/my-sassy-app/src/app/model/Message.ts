export class Message {
    constructor(public status : string ,
        public message : string ) {}
}

export const MessageOk : Message = {status: "Started" , message:"Server Has been Started"};
export const MessageError : Message = {status: "Stopped " , message:"Server Has been Stopped"};