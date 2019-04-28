package Thought;

public enum BasicTaskType 
{
	/*
	 * 0: move		(change the relative position vector)
	 * 		only applies to like moving on some surface
	 * 1: push		(exerting a force on an object)		
	 * 2: grab		(object enters your bag moves with you)
	 * 3: digest	(redistributes energy from consumed items to smaller packets)-v
	 * 4: breathe	(allows gas to permeate into the body) --------------------------------> both of these do not include the muscles required to do this.
	 * 5: extend	(begin to consider an object usable by yourself)
	 * 6: wait		(wait until some condition is met)
	 */
	move, push, grab, digest, breathe, extend, wait
}
