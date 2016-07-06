var MainController = function($scope){
    var self = $scope;

    self.platforms = [
             {name:"%S", img:"start.png"},
             {name:"%P", img:"portal.png"},
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
    self.mouseDown = false;

    self.setSel = function(nm){
        self.selName = nm;
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
        self.mouseDown = true;
    }

    self.cv.onmouseup = function(event){
        self.mouseDown = false;
    }

    self.cv.onmousemove = function(event){
        if(self.mouseDown){
            var x = ~~(event.layerX/35);
            var y = ~~(event.layerY/35);
            self.ctx.drawImage(document.getElementById("im-"+self.selName),x*35,y*35);
            self.map[""+x+","+(20-y)] = self.selName;
        }
    }

    self.downloadLevel = function(){
        var builder = "#Autogenerado con ATK Map Editor\n";
        for(var coords in self.map){
            builder += self.map[coords]+","+coords+",1,1\n";
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