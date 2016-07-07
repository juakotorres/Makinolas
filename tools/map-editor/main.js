var MainController = function($scope){
    var self = $scope;

    self.required = [
         {name:"%S", img:"start.png"},
         {name:"%P", img:"portal.png"},
    ];

    self.platforms = [
                     {"name":"CPL",img:"ClassicPrettyLeftCorner.png"},
                     {"name":"CU",img:"ClassicUp.png"},
                     {"name":"FPR",img:"FreezePrettyRightCorner.png"},
                     {"name":"FG",img:"FreezeWithoutGrass.png"},
                     {"name":"SRL",img:"SnowRoundedLeftCorner.png"},
                     {"name":"CPR",img:"ClassicPrettyRightCorner.png"},
                     {"name":"CG",img:"ClassicWithoutGrass.png"},
                     {"name":"FRL",img:"FreezeRoundedLeftCorner.png"},
                     {"name":"FGR",img:"FreezeWithoutGrassRounded.png"},
                     {"name":"SRR",img:"SnowRoundedRightCorner.png"},
                     {"name":"YB",img:"YellowBlock.png"},
                     {"name":"CRL",img:"ClassicRoundedLeftCorner.png"},
                     {"name":"CGR",img:"ClassicWithoutGrassRounded.png"},
                     {"name":"FRR",img:"FreezeRoundedRightCorner.png"},
                     {"name":"BL",img:"LineBox.png"},
                     {"name":"SSL",img:"SnowSimpleLeftCorner.png"},
                     {"name":"CRR",img:"ClassicRoundedRightCorner.png"},
                     {"name":"BC",img:"CrossBox.png"},
                     {"name":"FSL",img:"FreezeSimpleLeftCorner.png"},
                     {"name":"SSR",img:"SnowSimpleRightCorner.png"},
                     {"name":"CSL",img:"ClassicSimpleLeftCorner.png"},
                     {"name":"ZFL",img:"Flame.png"},
                     {"name":"FSR",img:"FreezeSimpleRightCorner.png"},
                     {"name":"SPL",img:"SnowPrettyLeftCorner.png"},
                     {"name":"SU",img:"SnowUp.png"},
                     {"name":"CSR",img:"ClassicSimpleRightCorner.png"},
                     {"name":"FPL",img:"FreezePrettyLeftCorner.png"},
                     {"name":"FU",img:"FreezeUp.png"},
                     {"name":"SPR",img:"SnowPrettyRightCorner.png"},
                     {"name":"SGR",img:"SnowWithoutGrassRounded.png"}
                     ];

    self.platforms.sort(function(a,b){return a.name.localeCompare(b.name)});

    self.pokemons = [
        {name:"Bagon",img:"Bagon.png"},
        {name:"Charmander",img:"Charmander.png"},
        {name:"Eevee",img:"Eevee.png"},
        {name:"Gastly",img:"Gastly.png"},
        {name:"Gible",img:"Gible.png"},
        {name:"Jigglypuff",img:"Jigglypuff.png"},
        {name:"Magnemite",img:"Magnemite.png"},
        {name:"Mareep",img:"Mareep.png"},
        {name:"Pichu",img:"Pichu.png"},
        {name:"Scyther",img:"Scyther.png"},
        {name:"Shinx",img:"Shinx.png"},
        {name:"Snivy",img:"Snivy.png"},
        {name:"Totodile",img:"Totodile.png"},
        {name:"Weedle",img:"Weedle.png"},
        {name:"Zubat",img:"Zubat.png"}
    ];

    self.map = {};

    self.selName = "BL";
    self.levelName = "level1"
    self.uniqueTool = false;
    self.deleteTool = false;
    self.startX = 0;
    self.startY = 0;

    self.setSel = function(nm, unq){
        self.selName = nm;
        self.uniqueTool = unq;
        self.deleteTool = false;
    };

    self.setDel = function(){
        self.deleteTool = true;
    }

    self.cv = document.getElementById("canvas");
    self.ctx = self.cv.getContext("2d");

    self.drawBaseLines = function(){
        self.ctx.beginPath();
        self.ctx.lineWidth = 1;
        self.ctx.strokeStyle = "#ccc";
        for(var i=0; i<150; i++){
            self.ctx.moveTo(35*i,0);
            self.ctx.lineTo(35*i,700);
        }
        for(var i=0; i<20; i++){
            self.ctx.moveTo(0,35*i);
            self.ctx.lineTo(5250,35*i);
        }
        self.ctx.stroke();
    }

    self.drawBaseLines();

    self.cv.onmousedown = function(event){
        var x = ~~(event.layerX/35);
        var y = ~~(event.layerY/35);
        if(self.deleteTool){
            self.deleteBlock(x,20-y);
        }
        else if(self.uniqueTool){
            self.ctx.drawImage(document.getElementById("im-"+self.selName),x*35,y*35);
            self.map[""+x+","+(20-y)] = self.selName;
        }
        else{
            self.startX = x;
            self.startY = y;
        }
    };

    self.cv.onmouseup = function(event){
        if(!self.uniqueTool && !self.deleteTool){
            var lastX = ~~(event.layerX/35);
            var lastY = ~~(event.layerY/35);
            var tag = ""+Math.min(self.startX,lastX)+","+Math.min(20-self.startY,20-lastY)+","+
                            (Math.abs(self.startX-lastX)+1)+","+(Math.abs(self.startY-lastY)+1);
            self.map[tag] = self.selName;
            for(var x=self.startX; x<=lastX; x++){
                for(var y=self.startY; y<=lastY; y++){
                    self.ctx.drawImage(document.getElementById("im-"+self.selName),x*35,y*35);
                }
            }
        }
    };

    self.downloadLevel = function(){
        var builder = "#ATK Map Editor\n";
        for(var coords in self.map){
            if(self.map[coords]!=null)
                builder += self.map[coords]+","+coords+"\n";
        }
        download(self.levelName+".lvl",builder);
    };

    self.deleteBlock = function(x,y){
        console.log(x+","+y);
        console.log(self.map);
        for(var coords in self.map){
            var comps = coords.split(",").map(function(e){return parseInt(e)});
            if(comps.length == 2 && comps[0]==x && comps[1]==y){
                self.map[coords] = null;
                self.ctx.clearRect(x*35,(20-y)*35,35,35);
                break;
            }
            else{
                if(comps[0]<=x && comps[0]+comps[2]>=x && comps[1]<=y && comps[1]+comps[3]>=y){
                    self.map[coords] = null;
                    console.log("borrado");
                    self.ctx.clearRect(comps[0]*35,(20-comps[1]-comps[3]+1)*35,35*comps[2],35*comps[3]);
                    self.drawBaseLines();
                    break;
                }
            }
        }
    };

};

function download(filename, text) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}