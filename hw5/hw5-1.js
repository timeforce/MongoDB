// Use the aggregation framework in the web shell to calculate the author with the greatest number of comments.
db.posts.aggregate([
	{$project : {_id : 0, "comments" : 1}}, 
	{$unwind : "$comments"}, 
	{$group : {_id : "$comments.author", count : {$sum : 1}}}, 
	{$sort : {"count" : -1}}, {$limit : 1}
])