"use strict";

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

    self.platforms.sort((a,b) => {return a.name.localeCompare(b.name)});

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

    self.blocks = [];
    self.rblocks = [];
    self.prefix = "";

    self.showing = {
        required: true,
        pokemons: true,
        rectangular: true,
        decorations: false
    };

    self.toggleShow = (name) => {
        self.showing[name] = !self.showing[name];
    };

    self.selName = "BL";
    self.levelName = "level1"
    self.uniqueTool = false;
    self.selecting = true;
    self.startX = 0;
    self.startY = 0;

    self.setSel = (nm, img, unq) => {
        self.selecting = false;
        self.closeInfo();
        self.selName = nm;
        self.selImg = img
        self.uniqueTool = (unq==null);
    };

    self.setSelect = () => {
        self.selecting = true;
    };

    self.clearAll = () => {
        self.blocks = [];
        self.rblocks = [];
    };

    self.mouseDown = (event) => {
        if(self.selecting) return;
        let cx = ~~((event.offsetX+16)/35);
        let cy = 39 - (~~((event.offsetY+28)/35));

        if(self.uniqueTool){
            self.blocks.push({
                name: self.selName,
                img: self.selImg,
                x: cx,
                y: cy,
                flip: false
            });
        }
        else{
            self.startX = cx;
            self.startY = cy;
        }
    };

    self.mouseUp = (event) => {
        if(self.selecting) return;
        if(!self.uniqueTool){
            let cx = ~~((event.offsetX+35)/35);
            let cy = 39 - (~~((event.offsetY+35)/35));
            self.rblocks.push({
                name: self.selName,
                img: self.selImg,
                x: Math.min(cx,self.startX),
                y: Math.min(cy,self.startY),
                width: Math.abs(cx-self.startX),
                height: Math.abs(cy-self.startY)
            });
        }
    };

    self.openInfo = (index) => {
        self.infoOpened = true;
        self.selectedBlock = self.blocks[index];
        self.selectedIndex = index;
        self.selectedRectangle = false;
        if(self.selectedBlock.name.indexOf("%E")!=-1){
            self.selectedTemplate = [{name: "x",type: "number"},{name: "y", type: "number"},{name: "flip", type: "checkbox"}];
        }
        else{
            self.selectedTemplate = [{name: "x",type: "number"},{name: "y", type: "number"}];
        }
    };

    self.openRInfo = (index) => {
        self.infoOpened = true;
        self.selectedBlock = self.rblocks[index];
        self.selectedIndex = index;
        self.selectedRectangle = true;
        self.selectedTemplate = [{name: "x",type: "number"},{name: "y", type: "number"},
                {name: "width", type: "number"},{name: "height", type: "number"}];
     };

     self.closeInfo = () => {
        self.infoOpened = false;
     };

     self.deleteElement = () => {
        if(self.selectedRectangle)
            self.rblocks.splice(self.selectedIndex,1);
        else
            self.blocks.splice(self.selectedIndex,1);
        self.closeInfo();
     };

    self.downloadLevel = () => {
        var builder = "#ATK Map Editor\n";
        self.rblocks.forEach((b) => {
            builder += b.name+","+b.x+","+b.y+","+b.width+","+b.height+"\n";
        });
        self.blocks.forEach((b) => {
            builder += b.name+","+b.x+","+b.y;
            if(b.modifier != null) builder += ","+self.modifiers.indexOf(b.modifier);
            builder += ","+b.flip;
            builder += "\n";
        });
        /*for(var coords in self.map){
            if(self.map[coords]!=null){
                var comps = self.map[coords].split(",");
                if(comps.length>2)
                    builder += comps[0]+","+comps[1]+","+coords+","+comps[2]+"\n";
                else
                    builder += comps[0]+","+coords+"\n";
            }
        }*/
        download(self.levelName+".lvl",builder);
    };

    self.loadLevel = () => {
        let file = document.getElementById("load").files[0];
        let fr = new FileReader();
        fr.onload = function(progress){
            self.blocks = [];
            self.rblocks = [];
            this.result.split("\n").forEach((line) => {
                let comps = line.split(",");
                if(comps.length < 2) ;
                else if(comps[0] == "%E")
                    self.blocks.push({
                        name: "%E,"+comps[1],
                        img: self.getImg(comps[1]),
                        x: +comps[2],
                        y: +comps[3],
                        modifier: self.modifiers[+comps[4]],
                        flip: comps[5] == true
                    });
                else if(comps[0].indexOf("%S")!=-1 || comps[0].indexOf("%P")!=-1)
                    self.blocks.push({
                        name: comps[0],
                        img: self.getImg(comps[0]),
                        x: +comps[1],
                        y: +comps[2]
                    });
                else if(comps[0].indexOf("%D")!=-1)
                    self.blocks.push({
                        name: comps[0]+","+comps[1],
                        img: self.getImg(comps[1]),
                        x: +comps[2],
                        y: +comps[3]
                    });
                else
                    self.rblocks.push({
                        name: comps[0],
                        img: self.getImg(comps[0]),
                        x: +comps[1],
                        y: +comps[2],
                        width: +comps[3],
                        height: +comps[4]
                    });
            });
            console.log(self.blocks);
            console.log(self.rblocks);
            $scope.$apply();
        };
        fr.readAsText(file);
    };

    self.getImg = (name) => {
        let r = self.required.filter((e) => {return e.name == name});
        if(r.length > 0) return r[0].img;
        let p = self.pokemons.filter((e) => {return e.name == name});
        if(p.length > 0) return p[0].img;
        let pl = self.platforms.filter((e) => {return e.name == name});
        if(pl.length > 0) return pl[0].img;
        let d = self.decorations.filter((e) => {return e.name == name});
        if(d.length > 0) return d[0].img;
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