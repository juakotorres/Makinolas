var MainController = function($scope){
    var self = $scope;

    self.required = [
         {name:"%S", img:"start.png"},
         {name:"%P", img:"portal.png"},
    ];

    self.platforms = [
             {name:"LP", img:"ClassicPrettyLeftCorner.png"},
             {name:"RR", img:"ClassicRoundedRightCorner.png"},
             {name:"P", img:"ClassicUp.png"},
             {name:"PR", img:"ClassicPrettyRightCorner.png"},
             {name:"SL", img:"ClassicSimpleLeftCorner.png"},
             {name:"RL", img:"ClassicRoundedLeftCorner.png"},
             {name:"SR", img:"ClassicSimpleRightCorner.png"}
         ];

    self.map = {};

    self.selName = "P";
    self.levelName = "level1"
    self.uniqueTool = false;
    self.startX = 0;
    self.startY = 0;

    self.setSel = function(nm, unq){
        self.selName = nm;
        self.uniqueTool = unq;
    }

    self.cv = document.getElementById("canvas");
    self.ctx = self.cv.getContext("2d");

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

    self.cv.onmousedown = function(event){
        var x = ~~(event.layerX/35);
        var y = ~~(event.layerY/35);
        if(self.uniqueTool){
            self.ctx.drawImage(document.getElementById("im-"+self.selName),x*35,y*35);
            self.map[""+x+","+(20-y)] = self.selName;
        }
        else{
            self.startX = x;
            self.startY = y;
        }
    }

    self.cv.onmouseup = function(event){
        if(!self.uniqueTool){
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
    }

    self.downloadLevel = function(){
        var builder = "#ATK Map Editor\n";
        for(var coords in self.map){
            builder += self.map[coords]+","+coords+"\n";
        }
        download(self.levelName+".lvl",builder);
    }

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