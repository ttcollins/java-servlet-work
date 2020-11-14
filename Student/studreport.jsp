<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:student>

<div class="container">
    <div class="card">
        <div class="card-header"><strong><h3>WEEKLY REPORT</h3></strong></div>
            <div class="card-body card-block">
                <form method="POST" action="studreport">

                    <div class="form-group"><label for="date" class=" form-control-label">Week Ending</label><input name="date" value="" type="date" id="date" required class="form-control"></div>

                    <div class="form-group"><label for="task_completed" class=" form-control-label">Task Completed</label><textarea rows="10" cols="30" name="task_completed" value="" type="text" id="task_completed" required class="form-control"></textarea></div>

                    <div class="form-group"><label for="task_in_progress" class=" form-control-label">Tasks in Progress</label><textarea rows="10" cols="30" name="task_in_progress" value="" type="text" id="task_in_progress" required class="form-control"></textarea></div>     
                    
                    <div class="form-group"><label for="next_day_tasks" class=" form-control-label">Next Day's Tasks</label><textarea rows="10" cols="30" name="next_day_tasks" value="" type="text" id="next_day_tasks" required class="form-control"></textarea></div>                                 

                    <div class="form-group"><label for="problems" class=" form-control-label">Problems/challenges</label><textarea rows="10" cols="30" name="problems" value="" type="text" id="problems" required class="form-control"></textarea></div> 
                    
                    <button type="submit" class="btn btn-primary" onclick="return validate()" >Submit</button>
                    <button type="reset" class="btn btn-primary">Refresh</button>
                </form>

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function validate() {
        var date = document.getElementById('date').value;
        var newdate = new Date(date);
        var now = new Date();
        if(newdate>now){
            alert("The Week Ending Cannot be in the Future.");
            return false;
        }
    }
</script>
</t:student>