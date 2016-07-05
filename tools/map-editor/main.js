var MainController = function($scope){
    var self = $scope;

    self.platforms = [
             {name:"LP", img:"ClassicPrettyLeftCorner.png"},
             {name:"RR", img:"ClassicRoundedRightCorner.png"},
             {name:"P", img:"ClassicUp.png"},
             {name:"PR", img:"ClassicPrettyRightCorner.png"},
             {name:"SL", img:"ClassicSimpleLeftCorner.png"},
             {name:"RL", img:"ClassicRoundedLeftCorner.png"},
             {name:"SR", img:"ClassicSimpleRightCorner.png"}
         ];

    self.selName = "P";

    self.setSel = function(nm){
        self.selName = nm;
    }

};