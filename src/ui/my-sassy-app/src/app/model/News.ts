export class News {
    constructor(
        public newsId : string,
        public author : string, 
        public  title: string,
        public  description: string,
        public  url: string,
        public  utlToImage: string,
        public  publishedAt: string,
        public  content: string,
        public childs : News [] = [],
        public count? : number 
    ) {
    }   

    /**This is the value that is used to identify a particular 
     * news in the object newsMapper in news-box-component.ts
     */
    public getUniqueHash() : string {
        return this.author+this.title + this.description;
    } 

    /**
     * Counts the number of repeated news item. 
     */
    public getCount() : number {
        if(this.childs == undefined ) {
            console.log("NOT CHILD" + " REPEAT COUNT : 1" )
            return 1; 
        }
        console.log("CHILDS PRESENT : " + (this.childs.length +1));
        return this.childs.length + 1; 
    }


}

