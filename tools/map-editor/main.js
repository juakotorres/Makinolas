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
                     {"name":"FSR",img:"FreezeSimpleRightCorner.png"},
                     {"name":"SPL",img:"SnowPrettyLeftCorner.png"},
                     {"name":"SU",img:"SnowUp.png"},
                     {"name":"CSR",img:"ClassicSimpleRightCorner.png"},
                     {"name":"FPL",img:"FreezePrettyLeftCorner.png"},
                     {"name":"FU",img:"FreezeUp.png"},
                     {"name":"SPR",img:"SnowPrettyRightCorner.png"},
                     {"name":"SGR",img:"SnowWithoutGrassRounded.png"}
                     ];

    self.modifiers = ["Stay","Normal","Stay and Shot","Fly wave and drop","Jumper","Follower","Fly Melee","Melee"];

    self.decorations = [
        {"name":"ZFL",img:"Flame.png"},
        {name:"SAS",img:"SnowArrowSign.png"},
        {name:"SCS",img:"SnowCrossSign.png"},
        {name:"SNM",img:"SnowMan.png"},
        {name:"SPT",img:"SnowPineTree.png"},
        {name:"SQS",img:"SnowQuestionSign.png"},
        {name:"SST",img:"SnowSquareTree.png"},
        {name:"SSs",img:"SnowStairs.png"},
        {name:"Tb1",img:"Tubo1.png"},
        {name:"vl1",img:"valla1.png"},
        {name:"vl2",img:"valla2.png"},
        {name:"vl3",img:"valla3.png"},
        {name:"AuT",img:"AutumnTree.png"},
        {name:"BCc",img:"BigCactus.png"},
        {name:"BST",img:"BonsaiSakuraTree.png"},
        {name:"CoT",img:"CoconutTree.png"},
        {name:"CuB",img:"CuteBush.png"},
        {name:"CuT",img:"CuteTree.png"},
        {name:"NST",img:"NormalSakuraTree.png"},
        {name:"PiT",img:"PineTree.png"},
        {name:"SmT",img:"SmallTree.png"},
        {name:"SpT",img:"SpringTree.png"},
        {name:"tr1",img:"tree-1.png"},
        {name:"tr2",img:"tree-2.png"},
        {name:"tro",img:"tree-ornament.png"}
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
        {name:"Zubat",img:"Zubat.png"},
        {name:"Ampharos",img:"Ampharos.png"},
        {name:"Arcanine",img:"Arcanine.png"},
        {name:"Beedrill",img:"Beedrill.png"},
        {name:"Beldum",img:"Beldum.png"},
        {name:"Butterfree",img:"Butterfree.png"},
        {name:"CastformC",img:"CastformC.png"},
        {name:"CastformN",img:"CastformN.png"},
        {name:"CastformS",img:"CastformS.png"},
        {name:"CastformW",img:"CastformW.png"},
        {name:"Caterpie",img:"Caterpie.png"},
        {name:"Charizard",img:"Charizard.png"},
        {name:"Charmeleon",img:"Charmeleon.png"},
        {name:"Crobat",img:"Crobat.png"},
        {name:"Croconaw",img:"Croconaw.png"},
        {name:"Cubone",img:"Cubone.png"},
        {name:"Drapion",img:"Drapion.png"},
        {name:"Feraligatr",img:"Feraligatr.png"},
        {name:"Flaffy",img:"Flaffy.png"},
        {name:"Gabite",img:"Gabite.png"},
        {name:"Garchomp",img:"Garchomp.png"},
        {name:"Gengar",img:"Gengar.png"},
        {name:"Geodude",img:"Geodude.png"},
        {name:"Gligar",img:"Gligar.png"},
        {name:"Gliscor",img:"Gliscor.png"},
        {name:"Golbat",img:"Golbat.png"},
        {name:"Golem",img:"Golem.png"},
        {name:"Graveler",img:"Graveler.png"},
        {name:"Growlithe",img:"Growlithe.png"},
        {name:"Gyarados",img:"Gyarados.png"},
        {name:"Haunter",img:"Haunter.png"},
        {name:"Igglybuff",img:"Igglybuff.png"},
        {name:"Kakuna",img:"Kakuna.png"},
        {name:"Kangaskhan",img:"Kangaskhan.png"},
        {name:"Luxray",img:"Luxray.png"},
        {name:"Magikarp",img:"Magikarp.png"},
        {name:"Magneton",img:"Magneton.png"},
        {name:"Magnezone",img:"Magnezone.png"},
        {name:"Marowak",img:"Marowak.png"},
        {name:"Masquerain",img:"Masquerain.png"},
        {name:"Metagross",img:"Metagross.png"},
        {name:"Metang",img:"Metang.png"},
        {name:"Metapod",img:"Metapod.png"},
        {name:"Ninetales",img:"Ninetales.png"},
        {name:"Pikachu",img:"Pikachu.png"},
        {name:"Raichu",img:"Raichu.png"},
        {name:"Rotom",img:"Rotom.png"},
        {name:"Salamence",img:"Salamence.png"},
        {name:"Sandshrew",img:"Sandshrew.png"},
        {name:"Sandslash",img:"Sandslash.png"},
        {name:"Scyzor",img:"Scyzor.png"},
        {name:"Serperior",img:"Serperior.png"},
        {name:"Servine",img:"Servine.png"},
        {name:"Shelgon",img:"Shelgon.png"},
        {name:"Skorupi",img:"Skorupi.png"},
        {name:"Spiritomb",img:"Spiritomb.png"},
        {name:"Surskit",img:"Surskit.png"},
        {name:"Vulpix",img:"Vulpix.png"},
        {name:"Wigglytuff",img:"Wigglytuff.png"}
    ];

    self.map = {};
    self.prefix = "";

    self.selName = "BL";
    self.levelName = "level1"
    self.uniqueTool = false;
    self.modifierTool = false;
    self.deleteTool = false;
    self.startX = 0;
    self.startY = 0;

    self.setSel = function(nm, unq, mod, prfx){
        self.selName = nm;
        self.uniqueTool = unq;
        if(mod == null) mod = false;
        self.modifierTool = mod;
        self.deleteTool = false;
        self.prefix = prfx;
    };

    self.setDel = function(){
        self.deleteTool = true;
    }

    self.cv = document.getElementById("canvas");
    self.ctx = self.cv.getContext("2d");
    self.ctx.font = "10px Arial";

    self.drawBaseLines = function(){
        self.ctx.beginPath();
        self.ctx.lineWidth = 1;
        self.ctx.strokeStyle = "#ccc";
        let width = 300;
        let height = 40;
        for(var i=0; i<width; i++){
            self.ctx.moveTo(35*i,0);
            self.ctx.lineTo(35*i,35*height);
        }
        for(var i=0; i<height; i++){
            self.ctx.moveTo(0,35*i);
            self.ctx.lineTo(35*width,35*i);
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
            if(self.selName.charAt(0) == "%")
                self.map[""+x+","+(20-y)] = self.selName+",0";
            else
                self.map[""+x+","+(20-y)] = self.prefix+","+self.selName+",0";
        }
        else if(self.modifierTool){
            if(self.map[""+x+","+(20-y)] != null){
                var comps = self.map[""+x+","+(20-y)].split(",");
                self.map[""+x+","+(20-y)] =  comps[0]+","+comps[1]+","+self.selName;
                self.ctx.fillText(self.selName,x*35,y*35+10);
            }
        }
        else{
            self.startX = x;
            self.startY = y;
        }
    };

    self.cv.onmouseup = function(event){
        if(!self.uniqueTool && !self.deleteTool && !self.modifierTool){
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
            if(self.map[coords]!=null){
                var comps = self.map[coords].split(",");
                if(comps.length>2)
                    builder += comps[0]+","+comps[1]+","+coords+","+comps[2]+"\n";
                else
                    builder += comps[0]+","+coords+"\n";
            }
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